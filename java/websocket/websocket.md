# WEBSOCKET

Websocket with spring-boot, angular 2, stomp js, sockjs

**Dependencies**

- sockjs-client (1.1.1)
- stompjs (2.3.3)
- @types/stompjs (2.3.0)
- spring-boot-starter-websocket (https://spring.io/guides/gs/messaging-stomp-websocket/)

## JAVA

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${order-web.security.origins}")
    private String[] origins;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/notification"); // backend sends to this path
        config.setApplicationDestinationPrefixes("/app"); // backend receives on this path
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins(origins).withSockJS();
    }

}
```

```
@Controller
public class OrderCreateController {

    @Autowired
    private OrderNotificationService orderNotificationService;

    @MessageMapping("/order/create")
    @SendTo("/notification/order")
    public OrderNotification receiveNotification(Long id) throws Exception {
        // just used for test when posting from client side
        return new OrderNotification().setType(OrderNotificationType.CREATE).setId(id);
    }
}
```

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void send(OrderNotificationType type, Long id) {
        // backend side is posting message to the queue /notification/...
        template.convertAndSend("/notification/order", new OrderNotification().setType(type).setId(id));
    }
}
```

```
public class OrderNotification implements Serializable {

    private static final long serialVersionUID = -2197114364342374733L;

    private OrderNotificationType type;
    private Long id;

    public OrderNotificationType getType() {
        return type;
    }

    public OrderNotification setType(OrderNotificationType type) {
        this.type = type;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderNotification setId(Long id) {
        this.id = id;
        return this;
    }

}
```

```
public enum OrderNotificationType {
    CREATE,
    UPDATE,
    DELETE,
    VALIDATE,
    CANCEL,
}
```


## Angular 2

- [TODO] manage auto reconnection on error

### webpack config

To avoid compilation error with webpack.

```
    node: {
      net: 'empty',
    },
```

### Stomp service

```
import { Observable, Subject } from 'rxjs/Rx';

import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

import { FiwStompConfig } from './stomp.config';

export class FiwStompWsService<R, S> {

    message$: Observable<R>;
    private message = new Subject<R>();

    private client: any;
    private config: FiwStompConfig;

    constructor() {
        this.message$ = this.message.asObservable();
    }

    init(config: FiwStompConfig) {
        this.config = config;
    }

    connect(config?: FiwStompConfig) {
        if (config) {
            this.config = config;
        }

        console.log('[service][stomp websocket] connecting...');
        this.client = Stomp.over(new SockJS(this.config.url));
        // let that = this;
        this.client.connect({}, (frame) => {
            console.log('[service][stomp websocket] connected ', frame);
            // this.connected = true;
            this.subscribe();
        });
    }

    subscribe() {
        console.log('[service][stomp websocket] subscribe');
        this.config.subscribe.forEach((path) => this.client.subscribe(path, (msg) => {
            let content: R = JSON.parse(msg.body);
            console.log('[service][stomp websocket] received message', content);
            this.message.next(content);
        }));
    }

    send(msg: S) {
        console.log('[service][stomp websocket] send', msg);
        this.config.publish.forEach((path) => this.client.send(path, {}, JSON.stringify(msg)));
    }

    disconnect() {
        this.client.disconnect(() => console.log('[service][stomp websocket] disconnecting...'));
    }

}
```

```
export interface FiwStompConfig {
  url: string;
  publish: string[];
  subscribe: string[];
};
```

```
import { Injectable } from '@angular/core';

import { FiwStompWsService } from '../../common/service/stomp';
import { FiwOrderNotification } from '../shared';

@Injectable()
export class FiwOrderNotificationWsService extends FiwStompWsService<FiwOrderNotification, number> {

    constructor() {
        super();
    }

}
```

```
export class FiwOrderNotification {
    type: FiwOrderNotificationType;
    id: number;
}
```

```
export enum FiwOrderNotificationType {
    CREATE,
    UPDATE,
    DELETE,
    VALIDATE,
    CANCEL,
}
```

```
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

@Component({
    selector: 'fiw-order-home',
    template: require('./order-home.component.html'),
    styles: [
        require('./order-home.component.scss')
    ],
})
export class FiwOrderHomeComponent implements OnInit {

    subscriptions: Subscription[] = new Array<Subscription>();

    constructor(
        private orderNotificationService: FiwOrderNotificationWsService,
    ) { }

    ngOnInit() {
        this.subscribe();
        this.connectWsNotification();
    }

    ngOnDestroy() {
        this.unsubscribe();
        this.disconnectWsNotification();
    }

    private subscribe() {
        this.subscriptions.push(this.orderNotificationService.message$.subscribe((msg) => this.receiveWsNotification(msg)));
    }

    private unsubscribe() {
        // prevent memory leak when component destroyed
        this.subscriptions.forEach((subscription) => subscription.unsubscribe());
    }

    private loadOrders() {
    }

    private reload() {
    }

    private connectWsNotification() {
        console.log('[view][order home] connect ws');
        this.orderNotificationService.connect({
            url: 'http://localhost:9000/websocket',
            publish: ['/app/order/create'],
            subscribe: ['/notification/order'],
        });
    }

    private receiveWsNotification(notification: FiwOrderNotification) {
        console.log('[view][order home] receiving message', notification);
        this.reload();
    }

    private sendWsNotification() {
        console.log('[view][order home] send');
        this.orderNotificationService.send(new Date().getTime());
    }

    private disconnectWsNotification() {
        // disconnect web socket
        this.orderNotificationService.disconnect();
    }

}
```

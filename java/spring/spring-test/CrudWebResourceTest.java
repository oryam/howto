package com.oryam.howto.test.integration.web.rest.gridSetting;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oryam.howto.common.util.test.CommonTest;
import com.oryam.howto.domain.api.DomainService;
import com.oryam.howto.domain.common.ItemDetail;
import com.oryam.howto.web.common.ItemDetailView;
import com.oryam.howto.web.rest.config.RestApiUrlConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrudWebResourceTest extends CommonTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DomainService domainService;


    @Test
    @Transactional
    @Commit
    public void should_response_created_item() throws Exception {

        String url = "/rest/api/item";

        ItemDetailView gridSetting = new ItemDetailView().setContent("new data");
        ResponseEntity<Long> response = restTemplate.postForEntity(url, gridSetting, Long.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Long settingId = response.getBody();
        assertThat(settingId).isNotNull();

        // ensure the query is performed with @Commit
        // - check data
        ItemDetail created = domainService.getDetail(settingId);
        assertThat(created.getContent()).isEqualTo("new data");

        // - delete the created data
        domainService.delete(settingId);
    }

}

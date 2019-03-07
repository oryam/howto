# HOWTO Secure

### How to generate a certificate?
To generate a certificate with company info.
```
keytool -genkey -alias myalias -keyalg RSA -keysize 2048 -keystore keystore.jks
```

To generate the CSR. Then post your CSR to the Certification Authority.
```
keytool -certreq -alias myalias -keyalg RSA -file file.csr -keystore keystore.jks
```

To import the p7p file with the chain (root, etc.).
``` 
keytool -import -trustcacerts -file certificate.p7b -keystore keystore.jks -storepass xxxx -alias myalias
```

Source reference: https://www.digicert.com/csr-creation-java.htm
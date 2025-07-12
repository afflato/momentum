keytool -genkeypair \
  -alias myapp \
  -keyalg RSA \
  -keysize 2048 \
  -storetype PKCS12 \
  -keystore myapp.p12 \
  -validity 3650 \
  -dname "CN=localhost, OU=IT, O=YourOrg, L=YourCity, ST=YourState, C=IN"
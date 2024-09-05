import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:8181/',
  realm: 'movie-recommendation',
  clientId: 'react-client-id',

});


export default keycloak;
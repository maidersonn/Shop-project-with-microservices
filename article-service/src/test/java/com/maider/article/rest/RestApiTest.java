package com.maider.article.rest;

import com.maider.article.articleFactory.ArticleFactory;
import com.maider.article.domain.entities.dto.ArticleCreationDTO;
import com.maider.article.domain.entities.dto.ArticleDTO;
import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.ArticleBuilder;
import com.maider.article.domain.entities.ArticleFilter;
import com.maider.article.domain.entities.User;
import com.maider.article.domain.repositories.ArticleRepository;
import com.maider.article.domain.repositories.UserRepositoy;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {
    @LocalServerPort
    private int port;
    @Autowired
    JpaRepository<Article, Long> jpaRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JWTUtils jwtAuthenticationConfig;
    @MockBean
    private ArticleRepository articleRepository;
    @MockBean
    private UserRepositoy userRepositoy;
    @AfterEach
    void clearDataBase() {
        jpaRepository.deleteAll();
    }

    @Test
    void shouldReturnArticleDTOForCreateEndpoint() {
        ArticleCreationDTO  creationDto= new ArticleCreationDTO("trousers", "leather", "Lewis", 40, 80.0);

        Article article = ArticleFactory.createOne();
        Article articleReturned = ArticleFactory.createOne();
        articleReturned.setId(1L);
        Mockito.when(articleRepository.save(article)).thenReturn(articleReturned);

        String[] roles = new String[] { "ROLE_ADMIN" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(creationDto, headers);

        ResponseEntity<ArticleDTO> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/article", request, ArticleDTO.class);
        ArticleDTO response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals("leathertrousers", response.getName());
        assertEquals( "Lewis", response.getBrand());
        assertEquals( 80.0, response.getPrice());
        assertEquals( 40, response.getSize());
        assertNotNull(response.getId());
    }
    @Test
    void shouldReturnListOfArticleDTOsForGetAllEndpoint() {
        List<Article> articles = ArticleFactory.create(2);
        Mockito.when(articleRepository.findAll()).thenReturn(articles);

        String[] roles = new String[] { "ROLE_USER" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(headers);

        List<ArticleDTO> response = this.restTemplate
                .exchange("http://localhost:" + port + "/articles",
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<List<ArticleDTO>>() {})
                .getBody();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Lewis", response.get(0).getBrand());
        response.forEach((element) -> assertInstanceOf(ArticleDTO.class, element));
    }
    @Test
    void shouldReturnArticleDTOForGetByIdEndpoint() {
        Article articleReturned = ArticleFactory.createOne();
        articleReturned.setId(1L);
        Mockito.when(articleRepository.getReferenceById(1L)).thenReturn(articleReturned);

        String[] roles = new String[] { "ROLE_USER" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ArticleDTO response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.GET, request, ArticleDTO.class).getBody();

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(articleReturned.getBrand(), response.getBrand());
        assertEquals(articleReturned.getMaterial().concat(articleReturned.getType()), response.getName());
        assertEquals( articleReturned.getPrice(), response.getPrice());
        assertEquals( articleReturned.getSize_(), response.getSize());
    }
    @Test
    void shouldReturnArticleNotFoundWhenObjectDoesNotExist() {
       Mockito.when(articleRepository.getReferenceById(1L)).thenThrow(new EntityNotFoundException());

        String[] roles = new String[] { "ROLE_USER" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(headers);

       String response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.GET, request, String.class).getBody();

       assertEquals("Article not found", response);
    }
    @Test
    void shouldReturnResponseEntityForDeleteEndpoint() {
        Article article = ArticleFactory.createOne();
        article.setId(1L);
        doAnswer(invocation -> null).when(articleRepository).deleteById(1L);

        String[] roles = new String[] { "ROLE_ADMIN" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(null, headers);



        ResponseEntity<?> response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.DELETE, request, ResponseEntity.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    void shouldReturnArticleDTOForUpdateEndpoint() {
        ArticleCreationDTO articleCreation = new ArticleCreationDTO("trousers", "leather", "Lewis", 40, 52.0);
        Article articleToUpdate = ArticleFactory.createOne();
        articleToUpdate.setId(1L);
        articleToUpdate.setPrice(52);
        Mockito.when(articleRepository.existsById(1L)).thenReturn(true);
        Mockito.when(articleRepository.save(articleToUpdate)).thenReturn(articleToUpdate);

        String[] roles = new String[] { "ROLE_ADMIN" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(articleCreation, headers);

        ResponseEntity<ArticleDTO> response = this.restTemplate.exchange("http://localhost:" + port + "/article/1", HttpMethod.PUT, request, ArticleDTO.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(52, response.getBody().getPrice());
    }
   @Test
    void shouldReturnFilteredList() {
        ArticleBuilder builder = new ArticleBuilder();
        List<Article> articles = new ArrayList<>();
        articles.add(builder.withBrand("Lewis").withPrice(80).withSize(38).withMaterial("leather").withType("trousers").build()) ;
        articles.add(builder.withBrand("Lewis").withPrice(60).withSize(39).withMaterial("leather").withType("trousers").build());

        ArticleFilter articleFilter = new ArticleFilter("trousers", 39, null, "leather", "Lewis", 80.0, null);
        Mockito.when(articleRepository.filter(articleFilter)).thenReturn(articles);

        String[] roles = new String[] { "ROLE_USER" };
        Mockito.when(userRepositoy.findByUsername("maidersonn")).thenReturn(new User(1L,"maidersonn", roles));

        String token = jwtAuthenticationConfig.getJWTToken("maidersonn");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> request = new HttpEntity<>(headers);

        List<ArticleDTO> response = this.restTemplate
                .exchange("http://localhost:" + port + "/articles/filtered?type=trousers&sizeLessThan=39&material=leather&brand=Lewis&priceLessThan=80.0",
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<List<ArticleDTO>>() {})
                .getBody();

        assertEquals(2, response.size());
        //assertEquals();

    }
}

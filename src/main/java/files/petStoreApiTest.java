package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static files.ReUsableMethods.rawToJsonArray;
import static io.restassured.RestAssured.given;

public class petStoreApiTest {
   static final String uri = "https://petstore.swagger.io/v2";

   @Test(dataProvider = "userData",priority=0)
    public void petStoreRegister(String id1 , String username1 , String email1 , String password1 ) {
        //register user
        RestAssured.baseURI = uri;
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.CreateUser(id1,username1,email1,password1)).when().post("user").then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(" this is register user " + response);
         }

    //login in the system
    @Test (dataProvider = "userData",priority=1)
    public void login(String userid, String uname ,String email ,String pw) {
        String username = uname;
        String password = pw;
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json")
                .queryParam("username", username).queryParam("password", password).get("/user/login").then()
                .statusCode(200).extract().asString();
        System.out.println(response);
        Assert.assertTrue(response.contains("logged in user"));
    }

    //Logout of the system
    @Test (priority = 2)
    public void logout() {
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json")
                .get("/user/logout").then()
                .statusCode(200).extract().asString();
        System.out.println(response);
        Assert.assertTrue(response.contains("ok"));

    }

    @Test(dataProvider ="petData",priority=3)

    public void addPet(String id , String petName) {
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.AddPet(id,petName)).when().post("/pet").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }

    @Test(dataProvider = "petData",priority=4)
    public void getPet(String id,String name) {
        RestAssured.baseURI =uri ;
        String petid = id;
        String expectedpetName = "chihuahua 2-year-old";
        String petResponse = given().log().all()
                .when().get("/pet/" + petid + "").then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(petResponse);
        String actualpetName = js1.getString("category.name");
        System.out.println("Asserting Result.......... ");
        Assert.assertEquals(actualpetName, expectedpetName);
    }

    //Upload the image of the dog.
    @Test (dataProvider = "petData",priority=5)
    public void uploadPetImage(String id, String name) {
        String filename = "@dog1.jpeg";
        String imgtype = "image/jpeg";
        String petid = id;
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json").header("Content-Type", "multipart/form-data").multiPart("type", imgtype).multiPart("file", filename)
                .when().post("/pet/"+petid+"/uploadImage").then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(" image is uploaded " + response);
    }
    @Test (dataProvider = "petData",priority=6)
    public void deletePet(String id, String name) {
        String petid = id;
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json")
                .when().delete("/pet/" + petid + "").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
        Assert.assertTrue(response.contains(petid));
    }


    //List all dogs
    @Test(priority =7)
    public void listAlldogs() throws ParseException {
        RestAssured.baseURI =uri ;
        String response = given().log().all().header("Content-Type", "application/json")
                .queryParam("status", "available").get("/pet/findByStatus").then()
                .statusCode(200).extract().asString();
             int dogCount = 0;
        JSONArray array1 = rawToJsonArray(response);
        for (Object jsonObject : array1) {
            JSONObject item = (JSONObject) jsonObject;
            if (item.containsKey("name") && item.get("name").equals("doggie")) {
                dogCount++;
                System.out.println(item.get("id"));
            }

        }
        System.out.println(dogCount);

    }

    @DataProvider(name="userData")
    public Object[][]  getUserData(){
        return new Object[][]{{"10010","tester10","tester10@qa.com","password"}};
    }
    @DataProvider(name="petData")
    public Object[][]  getPetData() {
        return new Object[][]{{"10009", "chihuahua 2-year-old"}}; }
}


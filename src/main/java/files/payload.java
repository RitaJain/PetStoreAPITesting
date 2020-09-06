package files;

public class payload {

public static String AddPet(String id ,String petname) {
        String addPet=
         "{  \"id\": "+id+",  \"category\": {    \"id\": 10002,    \"name\": \""+petname+"\"  },  \"name\": \"doggie\",  \"photoUrls\": [    \"/home/lenovo/Downloads/dog1.jpg\"  ],  \"tags\": [    {      \"id\": 10001,      \"name\": \"whitedoggy\"    }  ],  \"status\": \"available\"}";
        return addPet;
    }

    public static String CreateUser(String id,String username, String email ,String password ){
        String createUser=
         "{  \"id\": \""+id+"\",  \"username\": \""+username+"\",  \"firstName\": \"tester1\",  \"lastName\": \"user1\",  \"email\": \""+email+"\",  \"password\":\" "+password+"\", \"phone\": \"981602243\",  \"userStatus\": 1}" ;
        return createUser;
    }

}

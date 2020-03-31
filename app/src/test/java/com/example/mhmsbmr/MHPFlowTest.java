package com.example.mhmsbmr;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mhmsbmr.Login.LoginBmr;
import com.example.mhmsbmr.Login.MHPFlow;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import static com.example.mhmsbmr.Login.MHPFlow.decoded;

public class MHPFlowTest {

    MHPFlow mhp = new MHPFlow();

    @Test
    public void loginTest() throws Exception{
        String userName = "prashant";
        String password = "Test@123";
        LoginBmr lb = new LoginBmr();
        String salts[] = mhp.getSalt(userName);
        String hash = BCrypt.hashpw(password, salts[0]);
        String hash1 = BCrypt.hashpw(hash, salts[1]);
        System.out.println("hash " + hash);
        System.out.println("hash 1 " + hash1);
        String jwtToken = mhp.login(userName, hash1);
        System.out.println("-----------------------\n"+jwtToken);
        JSONObject object = new JSONObject(jwtToken);
        String token = object.getString("token");
        System.out.println("-----------------------");
        JSONObject result = new JSONObject(MHPFlow.decoded(jwtToken));
        //System.out.println(result.getString("profession"));
        //System.out.println(result.getString("userName"));
        //System.out.println(token);
        //System.out.println(result.getString("sessionToken"));
        //System.out.println(result.getString("userUUID"));
        //mhp.getuserbyuuid(token, result.getString( "userUUID"));
        System.out.println(mhp.getAssociatedOrg(token, result.getString("sessionToken")));


    }

    @Test
    public void decodeTest(){
        try {
            mhp.decoded("eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImU2NDE3NGI0LWI3NzctNGRlYS04NmUyLTNmMGQ1NDgwYTI2MiIsInNlc3Npb25FbmRUaW1lIjoxNTg1MzQ3NjUyLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODUzMDQ0NTIsInNlc3Npb25JZCI6IjFmMDdkMzQzLWFiODgtNGY0ZC04Yjc0LWI4YjVjNDIyY2M1MyIsInVzZXJOYW1lIjoidGVzdDAxIiwibGFzdExvZ2luU3RhcnQiOiIxNTg1MzA0NDIyODU5Iiwib3JnVVVJRCI6ImU2NDE3NGI0LWI3NzctNGRlYS04NmUyLTNmMGQ1NDgwYTI2MiIsIm5iZiI6MTU4NTMwNDQ1Miwib3JnUm9sZSI6Ik1IRUFkbWluIiwic2Vzc2lvblRva2VuIjoiU2Vzc2lvbklkOjE3Mi4zMS4xLjIyNCN0ZXN0MDE6ZTY0MTc0YjQtYjc3Ny00ZGVhLTg2ZTItM2YwZDU0ODBhMjYyOk1ITVM6TUhFQWRtaW4jMTU4NTMwNDQ1MTk1MSMxMTc2MTYyIzExNCIsInBlcnNvbklkIjoiNjI3NzMwODEtZGE4OS00NjYzLThiNmYtOWMwMGU5NDMyOTYwIiwidXNlclVVSUQiOiI1YzI0ZjU5Ni0wZjM0LTQxYTYtYWFmNS1lMzAwMmVhMmM0Y2UiLCJleHAiOjE1ODUzNDA0NTIsImlhdCI6MTU4NTMwNDQ1Mn0.oxMRj6A2fghk5IukZZO1FhEJp5vvXX9WO-sC59rIvpY");
        }catch (Exception e){}
    }

    @Test
    public void jsonTest(){
        //JWT jwt = new JWT("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ");
        String token = "eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImU2NDE3NGI0LWI3NzctNGRlYS04NmUyLTNmMGQ1NDgwYTI2MiIsInNlc3Npb25FbmRUaW1lIjoxNTg1MzQxMzYxLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODUyOTgxNjEsInNlc3Npb25JZCI6ImZlMmE5NzY2LTQ1MmYtNDdlYy1iMjUwLTI2YzQ5NjNiNjFkZSIsInVzZXJOYW1lIjoidGVzdDAxIiwibGFzdExvZ2luU3RhcnQiOiIxNTg1Mjk0ODg2NTY4Iiwib3JnVVVJRCI6ImU2NDE3NGI0LWI3NzctNGRlYS04NmUyLTNmMGQ1NDgwYTI2MiIsIm5iZiI6MTU4NTI5ODE2MSwib3JnUm9sZSI6Ik1IRUFkbWluIiwic2Vzc2lvblRva2VuIjoiU2Vzc2lvbklkOjE3Mi4zMS4xLjIyNCN0ZXN0MDE6ZTY0MTc0YjQtYjc3Ny00ZGVhLTg2ZTItM2YwZDU0ODBhMjYyOk1ITVM6TUhFQWRtaW4jMTU4NTI5ODE2MDczMyMtNzIzOTM2MzUzIzEwMCIsInBlcnNvbklkIjoiNjI3NzMwODEtZGE4OS00NjYzLThiNmYtOWMwMGU5NDMyOTYwIiwidXNlclVVSUQiOiI1YzI0ZjU5Ni0wZjM0LTQxYTYtYWFmNS1lMzAwMmVhMmM0Y2UiLCJleHAiOjE1ODUzMzQxNjEsImlhdCI6MTU4NTI5ODE2MX0.TFWVdCZYuUysbZ2gmQc5H2Eig4jwXcuiC_FTbDTUNis";
        try {
            DecodedJWT jwt = JWT.decode(token);
            System.out.println("jwt token "+jwt.getPayload());

        } catch (JWTDecodeException exception){
            //Invalid token
            exception.printStackTrace();
        }


       /* try {
            mhp.getJson("");
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    @Test
    public void jsonTest001(){
        try {
            mhp.getJson("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void jsonTest002(){
        try{
        mhp.getJson("IYfKRoMAUcSkWmvVEs1H2D2ennJ7gw7y_LPtXBYs97Y");
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void getAssociatedOrgTest(){
        System.out.println(new MHPFlow().getAssociatedOrg("",""));
    }



}

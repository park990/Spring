package com.sist.ex0903_kakaologin;

import mybatis.vo.MemberVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class LoginControl {
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login/kakao")
    public ModelAndView loginKakao(String code) {
        ModelAndView mv = new ModelAndView();

        // 로그인에 성공하게 되면 코드를 받는데 그 코드를 이용해서 token을 받아야한다.
        String access_Token = null;
        String refresh_Token = null;

        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        try {
            // 웹상의 경로를 객체화
            URL url = new URL(tokenUrl);

            // 웹 상의 경로와 연결하는 객체
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 카카오 서버에서 post 방식으로 요청을 원하므로 method post로 지정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // 카카오 서버로 파라미터 전달을 위해 스트림 생성
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream()));

            StringBuffer sb = new StringBuffer();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=045ad8ec819ec6a169e07d7aa6bedb78");
            sb.append("&redirect_uri=http://localhost:8080/login/kakao");
            sb.append("&code=").append(code);

            //위에서 만든 것을 갖고 서버로 보내자
            bw.write(sb.toString());
            bw.flush();

            //서버로 요청을 보낸 후 응답의 결과가 성공적이라면 responsecode를 받는다
            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode: " + responseCode); // 디버깅용 로그

            if (responseCode == 200) {
                // 요청을 통해 얻은 결과는 JSON타입의 결과 메시지를 읽어온다.
                // 그 결과를 받기(읽기) 위해 스트림 필요
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer result = new StringBuffer();
                String line = null;
                // 한줄 단위로 읽어서 result라는 StringBuffer에 적재하자.
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                System.out.println("RESULT:"+result.toString());

                // JSON 파싱으로 엑세스 토큰&리프레쉬 토큰 얻어내자
                JSONParser parser = new JSONParser();
//                Object obj = parser.parse(result.toString());
                JSONObject jsonObj = (JSONObject) parser.parse(result.toString());
                access_Token= (String)jsonObj.get("access_token");
                refresh_Token=(String) jsonObj.get("refresh_token");

                // 3번째 요청으로 사용자 정보를 갖고오자
                String apiURL="https://kapi.kakao.com/v2/user/me";
                String header ="Bearer "+access_Token;

                url= new URL(apiURL);
                conn= (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                // 파라미터를 보내자
                conn.setDoOutput(true);
                conn.addRequestProperty("Authorization",header);

                responseCode=conn.getResponseCode();
                System.out.println("responsecode="+responseCode);

                if(responseCode==200){
                    BufferedReader brdm=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer res= new StringBuffer();
                    String line2= null;
                    while((line2=brdm.readLine())!=null){
                        res.append(line2);
                    }
                    parser = new JSONParser();
                   JSONObject jsonObject = (JSONObject) parser.parse(res.toString());

                   // JSON 형태로 얻어내기
                   JSONObject kakaoaccount= (JSONObject) jsonObject.get("kakao_account");
                   JSONObject properties= (JSONObject) jsonObject.get("properties");

                   //얻어내서 이름과 메일만 빼내기
                   String name = (String)properties.get("nickname");
                   String email= (String)kakaoaccount.get("email");
                   String p_img= (String)properties.get("profile_image");
//                    System.out.println(name);
//                    System.out.println(email);
//                    System.out.println(p_img);
                    MemberVO mvo = new MemberVO();
                    mvo.setNickName(name);
                    mvo.setEmail(email);
                    mvo.setP_img(p_img);

                    //DB에 저장되어 있었던 사용자인지 판단 먼저 하자


                    mv.addObject("mvo",mvo);
                    mv.setViewName("registry");
                }

            }// if 의 끝


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}

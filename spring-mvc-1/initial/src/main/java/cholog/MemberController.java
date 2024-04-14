package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    // 동적으로 페이지 처리를 하기 위해서는 템플릿 엔진을 활용할 수 있습니다. --> templates 파일 하위에 hello.html 위치
    @GetMapping("/hello")
    // 쿼리 스트링(?name=brown)으로 전달된 name 값을 @RequestParam을 활용하여 컨트롤러 메서드의 파라미터로 주입 받습니다.
    // 컨트롤러 메서드 내에서 뷰로 값을 전달하기 위해서 Model 객체를 활용합니다.
    public String world(@RequestParam(name="name") String name, Model model) {
        // Model 객체는 컨트롤러 메서드의 파라미터로 주입 받을 수 있고, addAttribute 메서드를 통해 값을 전달할 수 있습니다.
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/json")
    // 컨트롤러 메서드의 리턴타입을 그대로 body에 담아 응답을 하기 위해서는 @ResponseBody를 활용할 수 있습니다.
    @ResponseBody
    public Person json() {
        return new Person("brown", 20);
    }
}

//package ee.taltech.webpage.controller;
//
//
//import ee.taltech.webpage.security.Roles;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("")
//@RestController
//public class IndexController {
//
////    @GetMapping
////    public String index() {
////        return "API is up";
////    }
//
//    @Secured(Roles.USER)
//    @GetMapping("user")
//    public String user() {
//        return "USER url";
//    }
//
//    @Secured(Roles.ADMIN)
//    @GetMapping("admin")
//    public String admin() {
//        return "ADMIN url";
//    }
//}

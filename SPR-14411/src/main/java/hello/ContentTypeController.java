package hello;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentTypeController {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> onError(RuntimeException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }

    @RequestMapping(value = "/text-plain-error", produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> textPlainWithError() {
        throw new IllegalArgumentException("oops");
    }

    @RequestMapping(value = "/application-json-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> applicationJsonWithError() {
        throw new IllegalArgumentException("oops");
    }


    @RequestMapping(value = "/text-plain", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String textPlain() {
        return "empty";
    }

    @RequestMapping(value = "/application-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> applicationJson() {
        return Collections.emptyMap();
    }

}

package vom.spring.domain.homepy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomepyController {
    private HomepyService homepyService;
    @Autowired
    public HomepyController(HomepyService homepyService) {
        this.homepyService = homepyService;
    }

    @GetMapping(value = "/api/homepy/{member-id}/profile")
    public ResponseEntity<?> getProfile(
            @PathVariable("member-id") Long memberId
    ) {
        return new ResponseEntity<>(homepyService.getProfile(memberId), HttpStatus.OK);
    }

    @GetMapping(value = "/api/homepy/{member-id}/greeting")
    public ResponseEntity<String> getGreeting(
            @PathVariable("member-id") Long memberId
    ) {
        // 추후에 member-id로 homepy-id 구하기
        Long homepyId = memberId;
        return new ResponseEntity<>(homepyService.getGreeting(homepyId), HttpStatus.OK);
    }

    @PutMapping (value = "/api/homepy/{member-id}/greeting")
    public ResponseEntity<HttpStatus> setGreeting(
            @PathVariable("member-id") Long memberId,
            @RequestParam String greeting
    ) {
        // 추후에 member-id로 homepy-id 구하기
        Long homepyId = memberId;
        homepyService.setGreeting(homepyId, greeting);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

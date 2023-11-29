import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.exams.Services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @DeleteMapping("/open/{id}")
    public ResponseEntity<?> deleteOpenQuestion(@PathVariable Integer id) {
        questionService.deleteOpenQuestion(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/closed/{id}")
    public ResponseEntity<?> deleteClosedQuestion(@PathVariable Integer id) {
        questionService.deleteClosedQuestion(id);
        return ResponseEntity.ok().build();
    }

}
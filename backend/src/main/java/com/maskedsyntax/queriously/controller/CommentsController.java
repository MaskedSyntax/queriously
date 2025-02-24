// package com.maskedsyntax.queriously.controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.maskedsyntax.queriously.dto.AnswerRequestDTO;
// import com.maskedsyntax.queriously.dto.AnswerResponseDTO;
// import com.maskedsyntax.queriously.service.AnswerService;

// @RestController
// @RequestMapping("/answers")
// public class CommentsController {
//     private final AnswerService answerService;

//     /**
//      * @param answerRepository
//      */
//     public AnswerController(AnswerService answerService) {
//         this.answerService = answerService;
//     }

//     @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<AnswerResponseDTO> addAnswer(@RequestBody AnswerRequestDTO answerRequestDTO) {
//         AnswerResponseDTO answerResponseDTO = answerService.saveAnswer(answerRequestDTO);
//         return ResponseEntity.ok(answerResponseDTO);
//     }

    
//     @GetMapping("/{id}")
//     public ResponseEntity<AnswerResponseDTO> getAnswerById(@PathVariable Long id) {
//         AnswerResponseDTO answerResponseDTO = answerService.getAnswerById(id);
//         return new ResponseEntity<>(answerResponseDTO, HttpStatus.OK);
//     }

    
//     @GetMapping("")
//     public ResponseEntity<List<AnswerResponseDTO>> getAllAnswers() {
//         List<AnswerResponseDTO> answers = answerService.getAnswers();
//         return ResponseEntity.ok(answers);
//     }

    
//     @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<AnswerResponseDTO> updateAnswer(@PathVariable Long id,
//             @RequestBody AnswerRequestDTO answerRequestDTO) {
//         AnswerResponseDTO answerResponseDTO = answerService.updateAnswer(id, answerRequestDTO);
//         return ResponseEntity.ok(answerResponseDTO);
//     }

    
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
//         answerService.deleteAnswer(id);
//         return ResponseEntity.noContent().build();
//     }
// }

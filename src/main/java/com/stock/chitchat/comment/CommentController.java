package com.stock.chitchat.comment;

import com.stock.chitchat.comment.dto.CommentCreateUpdateRequestDTO;
import com.stock.chitchat.comment.dto.CommentPageResponseDTO;
import com.stock.chitchat.common.CurrentUser;
import com.stock.chitchat.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks/{stockId}/posts/{postId}/comments")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentCreateUpdateRequestDTO requestDTO,
            @CurrentUser User user
    ) {
        Long commentId = commentService.createComment(postId, requestDTO, user);
        return ResponseEntity.ok(commentId);
    }

    @GetMapping
    public ResponseEntity<Page<CommentPageResponseDTO>> getCommentsPage(
            @PathVariable("postId") Long postId,
            Pageable pageable
    ) {
        Page<CommentPageResponseDTO> data = commentService.getCommentsPage(postId, pageable);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentCreateUpdateRequestDTO requestDTO,
            @CurrentUser User user
    ) {
        commentService.updateComment(commentId, requestDTO, user);
        return ResponseEntity.ok("success");
    }
}

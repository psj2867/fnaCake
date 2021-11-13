package ml.psj2867.fancake.service.comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import ml.psj2867.fancake.dao.CommentEntityDao;
import ml.psj2867.fancake.entity.UserEntity;
import ml.psj2867.fancake.entity.VideoEntity;
import ml.psj2867.fancake.service.comment.model.CommentDto;
import ml.psj2867.fancake.service.comment.model.CommentForm;
import ml.psj2867.fancake.service.comment.model.CommentOffsetForm;
import ml.psj2867.fancake.service.user.UserService;
import ml.psj2867.fancake.service.video.VideoService;

@Service
public class CommentService {
    
    @Autowired
    CommentEntityDao commentDao;
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;

    public Page<CommentDto> getComments(int videoIdx, CommentOffsetForm commentOffsetForm){
        VideoEntity video = videoService.getVideoEntityOrThrow(videoIdx);
        Page<CommentDto> comments = commentDao.findAll(commentOffsetForm.toSpec(video), commentOffsetForm.toPageable())
                        .map(CommentDto::of);                        
        if(commentOffsetForm.desc())
            return this.reverse(comments);
        return comments;
    }
    private Page<CommentDto> reverse(Page<CommentDto> comments ){
        List<CommentDto> contents = new ArrayList<>( comments.toList() );
        Collections.reverse(contents);
        Page<CommentDto> reversedComments = new PageImpl<>(contents, comments.getPageable(), comments.getTotalElements());
        return reversedComments;
    }
    public void doComment(int videoIdx, CommentForm commentForm){
        VideoEntity video = videoService.getVideoEntityOrThrow(videoIdx);
        UserEntity user = userService.getUserOrThrow();
        commentDao.save(commentForm.toEntity(user, video));
    }

}

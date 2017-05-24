package com.example.alumna.model.Interface;

import com.example.alumna.model.MemberCircleModel;

/**
 * Created by Leebobo on 2017/5/24.
 */

public interface MemberCircleModelImpl {
    void getUser(final int uid);
    void getCircle(final int uid);
    void getlikeList(final int tid, MemberCircleModel.OnLikeListResult listener);
    void getCommentList(final int tid, MemberCircleModel.OnCommentResult listener);
}

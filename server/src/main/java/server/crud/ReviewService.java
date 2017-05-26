package server.crud;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.User;
import com.ubb.cms.utils.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.IRepository;
import repository.ReviewRepository;
import server.validator.ValidatorInterface;

import java.util.List;

/**
 * Created by Cosmin Pavel on 26.05.2017.
 */
@Component
public class ReviewService extends BaseService<Review> {
    private ReviewRepository reviewRepository;

    @Autowired
    ReviewService(ValidatorInterface validator,ReviewRepository reviewRepository) {
        super(validator);
        this.reviewRepository=reviewRepository;
    }

    @Override
    IRepository<Review> getRepository() {
        return reviewRepository;
    }

    public List<Review> getReviewByReviewerAndStatus(User user,ReviewStatus status){
        return reviewRepository.getReviewByReviewerAndStatus(user,status);
    }





}

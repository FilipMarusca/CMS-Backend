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

import java.util.ArrayList;
import java.util.Collection;
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

    public Collection<Review> findBy(Paper paper) {
        return reviewRepository.findBy(paper);
    }

    @Override
    IRepository<Review> getRepository() {
        return reviewRepository;
    }

    public List<Review> getReviewByReviewerAndStatus(User user,ReviewStatus status){
        return reviewRepository.getReviewByReviewerAndStatus(user,status);
    }
    public List<Paper> getPaperToBeReviewed(User u ,ReviewStatus s){
        List<Paper> lista=new ArrayList<>();
        for (Review r:
            getReviewByReviewerAndStatus(u,s)) {
            lista.add(r.getUserPaper().getPaper());
        }
        return lista;
    }

    public void delete(Review r){
        reviewRepository.delete(r);
    }
    public List<Review> getReviewsByReviewer(User user)
    {
        return reviewRepository.getReviewsByReviewer(user);
    }

    public void changeReviewToConfirmedToBeReviewed(Review review)
    {
        reviewRepository.changeReviewToConfirmedToBeReviewed(review);
    }

    public void changeReviewToRefusedToBeReviewed(Review review)
    {
        reviewRepository.changeReviewToRefusedToBeReviewed(review);
    }
    public Review getReviewByReviewerAndPaper(User u,Paper p){
        return reviewRepository.getReviewByReviewerAndPaper(u,p);
    }

}

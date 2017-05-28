package repository;

import com.ubb.cms.Paper;
import com.ubb.cms.Review;
import com.ubb.cms.User;
import com.ubb.cms.utils.ReviewStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin Pavel on 26.05.2017.
 */
@Repository
public class ReviewRepository extends AbstractRepository<Review>{
    @Autowired
    public ReviewRepository(SessionFactory sessionFactory) {
        super(sessionFactory,Review.class);
    }

    public List<Review> getReviewByReviewerAndStatus(User user,ReviewStatus status) {
        List<Review> lista = new ArrayList<>();
        List<Review> lista2=getAll();
        for (Review r :lista2) {
            if (r.getStatus().equals(status) && r.getUserPaper().getUser().getId()==user.getId()) {
                lista.add(r);
                //System.out.println(r.toString());

            }

        }
            return lista;

    }

    public void delete(Review r){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(r);
        transaction.commit();
    }
    public List<Review> getReviewsByReviewer(User user)
    {
        List<Review> lista = new ArrayList<>();
        for(Review r : getAll())
        {
            if(r.getUserPaper().getUser().getId() == user.getId())
            {
                lista.add(r);
            }
        }

        return lista;
    }
    public synchronized Review getReviewByReviewerAndPaper(User u,Paper p){
        for(Review r:getAll()){
            if(r.getUserPaper().getUser().getId()==u.getId()&&r.getUserPaper().getPaper().getId()==p.getId()){
                return r;
            }
        }
        throw null;
    }
    private void changeReviewStatus(Review review, ReviewStatus newStatus)
    {
        review.setStatus(newStatus);
        update(review);
    }

    public void changeReviewToConfirmedToBeReviewed(Review review)
    {
        changeReviewStatus(review, ReviewStatus.ConfirmedToBeReviewed);
    }

    public void changeReviewToRefusedToBeReviewed(Review review)
    {
        changeReviewStatus(review, ReviewStatus.RefusedToBeReviewed);
    }
}
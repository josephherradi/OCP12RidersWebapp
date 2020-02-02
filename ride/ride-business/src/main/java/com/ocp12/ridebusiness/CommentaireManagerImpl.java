package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.CommentaireDao;
import com.ocp12.ridemodele.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireManagerImpl implements  CommentaireManager{
    @Autowired
    private CommentaireDao commentaireDao;

    @Override
    public void saveCommentaire(Commentaire lecommentaire) {
        commentaireDao.save(lecommentaire);

    }

    @Override
    public List<Commentaire> sortieCommentairesList(Integer sortieId) {
        return commentaireDao.findBySortie(sortieId);
    }
}

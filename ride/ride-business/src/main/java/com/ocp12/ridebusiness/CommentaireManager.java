package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Commentaire;

import java.util.List;

public interface CommentaireManager {
    public void saveCommentaire(Commentaire lecommentaire);
    public List<Commentaire> sortieCommentairesList(Integer sortieId);
}

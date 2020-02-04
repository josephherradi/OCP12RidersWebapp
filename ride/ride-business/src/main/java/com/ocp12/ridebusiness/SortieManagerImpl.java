package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.SortieDao;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SortieManagerImpl implements SortieManager {
    @Autowired
    SortieDao sortieDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Sortie> sortiesList() {
        return sortieDao.findAll();
    }

    @Override
    public void saveSortie(Sortie laSortie) {
        sortieDao.save(laSortie);

    }

    @Override
    public Sortie findById(Integer sortieId) {
        return sortieDao.findById(sortieId).orElse(null);
    }

    @Override
    public Sortie findByFilename(String kmlname) {
        return sortieDao.findByFilename(kmlname);
    }

    @Override
    public List<Sortie> organisateurSorties(Utilisateur organisateur) {
        return sortieDao.findByOrganisateur(organisateur);
    }

    @Override
    public List<Sortie> searchSorties(String statut, Boolean horspiste, String niveau) {
        String queryDyn = "select s from Sortie s where 1=1";

        if (!StringUtils.isEmpty(statut)) {
            queryDyn = queryDyn + " and s.statut= :statut";
        }
        if (!StringUtils.isEmpty(horspiste)) {
            queryDyn = queryDyn + " and s.horspiste= :horspiste";
        }
        if (!StringUtils.isEmpty(niveau)) {
            queryDyn = queryDyn + " and s.niveau= :niveau";
        }

        Query<Sortie> query= (Query<Sortie>) entityManager.createQuery(queryDyn,Sortie.class);

        if (!StringUtils.isEmpty(statut)) {
            query.setParameter("statut",statut);
        }
        if (!StringUtils.isEmpty(horspiste)) {
            query.setParameter("horspiste",horspiste);
        }
        if (!StringUtils.isEmpty(niveau)) {
            query.setParameter("niveau",niveau);
        }

        List<Sortie> sortieList=query.getResultList();
        return sortieList;
    }
}

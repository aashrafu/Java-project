package com.learnwords.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learnwords.entity.TrainingEntity;
import com.learnwords.entity.WordEntity;
import com.learnwords.enums.TrainingType;

@Repository
public class TrainingDAO extends DAOGeneric<TrainingEntity>{

	@Override
	public TrainingEntity find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TrainingEntity findByType(TrainingType type) {
		em.getTransaction().begin();
		
		List<TrainingEntity> trainings = em.createQuery("SELECT t FROM trining t WHERE training_name='" + type.getName() +"'").getResultList();

		em.getTransaction().commit();
		
		if(trainings.size() > 0)
		{
			return trainings.get(0);
		}
		
		return null;
	}
}

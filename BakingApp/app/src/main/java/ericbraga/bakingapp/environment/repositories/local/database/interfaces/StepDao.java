package ericbraga.bakingapp.environment.repositories.local.database.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.entity.StepEntity;

@Dao
public interface StepDao {
    @Insert
    void insert(StepEntity stepEntity);

    @Update
    void update(List<StepEntity> stepsEntity);
}

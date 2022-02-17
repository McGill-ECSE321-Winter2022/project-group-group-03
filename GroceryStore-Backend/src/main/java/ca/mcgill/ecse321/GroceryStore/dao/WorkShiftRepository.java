package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.WorkShift;

public interface WorkShiftRepository extends CrudRepository<WorkShift, Integer>{

    WorkShift findWorkShiftByShiftID(Integer shiftID);

}
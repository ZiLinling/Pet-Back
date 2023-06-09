package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Pet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
public interface PetMapper extends BaseMapper<Pet> {
    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id AND breed.name = '${breedName}'")
    Page<petVO> pageByBreedName(@Param("page") Page<petVO> page, @Param("breedName") String breedName);

    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id AND breed.name like '%${petName}%'")
    Page<petVO> pageByName(@Param("page") Page<petVO> page, @Param("petName") String petName);

    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id AND breed.name like '%${petName}%'")
    List<petVO> listByName(@Param("petName") String petName);

    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id")
    Page<petVO> pageByAllBreedName(@Param("page") Page<petVO> page);

    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id AND breed.specie ='${specie}'")
    Page<petVO> pageBySpecie(@Param("page") Page<petVO> page, @Param("specie") Integer specie);

    @Select("SELECT pet.*,breed.name as breedName,breed.specie FROM pet,breed WHERE pet.breed_id = breed.id AND breed.specie ='${specie}'")
    List<petVO> listBySpecie(@Param("specie") Integer specie);

}

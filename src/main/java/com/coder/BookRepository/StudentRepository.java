package com.coder.BookRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.coder.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>  {



}

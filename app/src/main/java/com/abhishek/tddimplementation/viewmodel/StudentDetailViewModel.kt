package com.abhishek.tddimplementation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abhishek.tddimplementation.model.Student

class StudentDetailViewModel {
    private val _studentInfo = MutableLiveData<Student>()
    val studentInfo: LiveData<Student>
        get() = _studentInfo

    /*
    * Here we would load the student's information from our data source
    * For now, let's create a sample student object
    *
    * @param studentId The id of the student
    * */
    fun loadStudentInfo(studentId: String) {
        val student = Student(
            name =  "Ideal Name",
            rollNumber = "20",
            dateOfBirth = "2000-01-01",
            address ="Ideal Address",
            gender = "Male"
        )
        _studentInfo.value = student
    }

}
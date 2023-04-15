package com.abhishek.tddimplementation

import com.abhishek.tddimplementation.viewmodel.StudentDetailViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class StudentDetailViewModelTest {
    private lateinit var viewModel: StudentDetailViewModel

    @Before
    fun setUp() {
        viewModel = StudentDetailViewModel()
    }

    @Test
    fun testViewModelInstantiation() {
        assertNotNull(viewModel)
    }

    @Test
    fun testLoadStudent() {
        // Set up
        val studentDetailViewModel = StudentDetailViewModel()
        val expectedName = "Ideal Name"
        val expectedRollNo = "20"
        val expectedDob = "2000-01-01"
        val expectedAddress = "Ideal Address"
        val expectedGender = "Male"

        val rollNo = "20"
        // Exercise
        studentDetailViewModel.loadStudentInfo(rollNo)

        // Verify
        assertEquals(expectedName, studentDetailViewModel.studentInfo.value?.name ?: "")
        assertEquals(expectedRollNo, studentDetailViewModel.studentInfo.value?.rollNumber)
        assertEquals(expectedDob, studentDetailViewModel.studentInfo.value?.dateOfBirth ?: "")
        assertEquals(expectedAddress, studentDetailViewModel.studentInfo.value?.address ?: "")
        assertEquals(expectedGender, studentDetailViewModel.studentInfo.value?.gender ?: "")
    }
}

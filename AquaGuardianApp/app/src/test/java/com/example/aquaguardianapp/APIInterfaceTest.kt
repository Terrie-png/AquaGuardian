package com.example.aquaguardianapp

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class APIInterfaceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiInterface: APIInterface

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIInterface::class.java)
    }

    @Test
    fun testRegisterUser(){
        val mockResponse = MockResponse()
        mockResponse.setBody("""{
         "success": true
    }""")

        mockWebServer.enqueue(mockResponse)
        // Create a mock User object
        val mockUser = User(
            username = "testuser",
            name = "Test User",
            email = "test@example.com",
            phone = "1234567890",
            address = "123 Test Street",
            userUID = null
        )
        val response = apiInterface.registerUser(mockUser).execute()

        // Assert the expected properties of the response
        val responseBody = response.body()
        Assert.assertTrue("Response should indicate success", responseBody?.success == true)
    }

    @Test(timeout = 10000) // Timeout of 10 seconds
    fun testRegisterUser_returnSuccessResponse() {
        try {
            val content = Helper.readFileResource("/response.json")
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(200)
            mockResponse.setBody(content)
            mockWebServer.enqueue(mockResponse)

            val mockUser = User(
                username = "john123",
                name = "John Doe",
                email = "Doe@gmail.com",
                phone = "1234567890",
                address = "123 Test Street",
                userUID = "uid12345"
            )

            val response = apiInterface.registerUser(mockUser).execute()

            val responseBody = response.body()
            Assert.assertTrue("Response should indicate success", responseBody?.success == true)
        }
        catch (e: Exception){
            Assert.fail("Error reading resource file: ${e.message}")
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
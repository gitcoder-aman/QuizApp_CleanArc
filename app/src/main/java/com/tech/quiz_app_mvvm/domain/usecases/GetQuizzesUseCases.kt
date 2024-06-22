package com.tech.quiz_app_mvvm.domain.usecases

import com.tech.quiz_app_mvvm.common.Resource
import com.tech.quiz_app_mvvm.data.remote.dto.QuizResponse
import com.tech.quiz_app_mvvm.domain.model.Quiz
import com.tech.quiz_app_mvvm.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuizzesUseCases(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(  //feature of kotlin
        amount : Int,
        category : Int,
        difficulty : String,
        type: String
    ): Flow<Resource<QuizResponse>> = flow{

        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(
                    data = quizRepository.getQuizzes(
                        amount,
                        category,
                        difficulty,
                        type
                    )
                )
            )
        }catch (e : Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}
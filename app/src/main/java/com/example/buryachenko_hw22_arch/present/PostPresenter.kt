package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.domain.PostMapper
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.present.PostUIMapper
import com.example.buryachenko_hw22_arch.tools.CancellableOperation
import com.example.buryachenko_hw22_arch.tools.Result

interface PostView {
    fun showPost(post: PostUIModel)
    fun showError(error: String)
}

class PostPresenter(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper
) {
    private var view: PostView? = null
    private var cancellableOperation: CancellableOperation? = null

    fun attachView(postView: PostView) {
        view = postView

        cancellableOperation = postRepository.getInfo()
            .map(postUIMapper::map)
            .postOnMainThread(::showResult)

    }

    fun detachView() {
        view = null
        cancellableOperation?.cancel()
    }

    private fun showResult(result: Result<PostUIModel, String>) {
        if (result.isError) {
            view?.showError(result.errorResult)
        } else {
            view?.showPost(result.successResult)
        }
    }
}
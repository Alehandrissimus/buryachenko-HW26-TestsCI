package com.example.buryachenko_hw22_arch.present

import com.example.buryachenko_hw22_arch.data.PostRepository
import com.example.buryachenko_hw22_arch.domain.PostModel
import com.example.buryachenko_hw22_arch.tools.CancellableOperation
import com.example.buryachenko_hw22_arch.tools.Result

interface PostView {
    fun showError(error: String)
    fun setupPostList(list: List<PostModel>)
}

class PostPresenter(
    private val postRepository: PostRepository,
) {
    private var view: PostView? = null
    private var cancellableOperation: CancellableOperation? = null

    fun attachView(postView: PostView) {
        view = postView

        cancellableOperation = postRepository.getInfo()
            .postOnMainThread(::showResult)
    }

    fun detachView() {
        view = null
        cancellableOperation?.cancel()
    }

    private fun showResult(result: Result<List<PostModel>, String>) {
        if (result.isError) {
            view?.showError(result.errorResult)
        } else {
            view?.setupPostList(result.successResult)
        }
    }
}
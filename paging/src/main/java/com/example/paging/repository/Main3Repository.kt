package com.example.paging.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.common.base.BaseRepository
import com.example.paging.bean.GithubAccount
import com.example.paging.net.AgentApi

class Main3Repository : BaseRepository() {

    private val toLiveData = Main3DataSourceFactory().toLiveData(
        config = Config(
            pageSize = 20,
            enablePlaceholders = false,
            maxSize = 200
        )
    )


    fun getAll() : LiveData<PagedList<GithubAccount>>{
        return toLiveData
    }

    inner class Main3DataSourceFactory : DataSource.Factory<Long, GithubAccount>() {
        override fun create(): DataSource<Long, GithubAccount> {
            return Main3ItemKeyedDataSource()
        }

    }

    /**
     * 1.PageKeyedDataSource: 通过当前页相关的key来获取数据，非常常见的是key作为请求的page的大小。
     * 2.ItemKeyedDataSource: 通过具体item数据作为key，来获取下一页数据。例如聊天会话，请求下一页数据可能需要上一条数据的id。
     * 3.PositionalDataSource: 通过在数据中的position作为key，来获取下一页数据。这个典型的就是上面所说的在Database中的运用。
     *
     * 四步实现Paging对网络数据的处理
     * 1.基于以上3个DataSource实现网络请求
     * 2.实现DataSource.Factory
     * 3.使用LiveData来观察PagedList
     * 4.使用PagedListAdapter来与数据进行绑定与更
     */
    inner class Main3ItemKeyedDataSource : ItemKeyedDataSource<Long, GithubAccount>() {
        override fun loadInitial(
            params: LoadInitialParams<Long>,
            callback: LoadInitialCallback<GithubAccount>
        ) {
            launch(
                block = {
                    val githubAccount =
                        AgentApi.getOtherApiService().getGithubAccount(0, params.requestedLoadSize)
                    callback.onResult(githubAccount)
                }
            )
        }

        override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<GithubAccount>) {
            launch(
                block = {
                    val githubAccount =
                        AgentApi.getOtherApiService()
                            .getGithubAccount(params.key, params.requestedLoadSize)
                    callback.onResult(githubAccount)
                },
                error = {

                }
            )
        }

        override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<GithubAccount>) {
        }

        override fun getKey(item: GithubAccount): Long = item.id.toLong()

    }
}
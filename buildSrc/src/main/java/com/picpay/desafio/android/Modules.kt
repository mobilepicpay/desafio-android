package com.picpay.desafio.android

object Modules {
    const val testLib = ":testlib"
    const val app = ":app"
    const val ui = ":ui"
    const val network = ":network"

    const val contactListPresentation = ":feature:contactlist:presentation"
    const val contactListInternalDataSource = ":feature:contactlist:datasource:internal"
    const val contactListRemoteDataSource = ":feature:contactlist:datasource:remote"
    const val contactListRepositoryImpl = ":feature:contactlist:repository:impl"
    const val contactListRepository = ":feature:contactlist:repository:public"
    const val contactListUseCaseImpl = ":feature:contactlist:usecase:impl"
    const val contactListUseCase = ":feature:contactlist:usecase:public"
}

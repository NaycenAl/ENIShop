package com.example.enishop

interface EniShopDestination {

val route: String

}

object ArticleListScreen : EniShopDestination {
    override val route: String
        get()=  "articleListScreenRoute"

}



object ArticleDetailsScreen : EniShopDestination {
    override val route: String
        get()=  "articleDetailsScreenRoute"

    val argArticleId = 1

    val arguments = listOf(androidx.navigation.navArgument(argArticleId.toString()) {
        type = androidx.navigation.NavType.LongType
    })

    val routeWithArgs = "$route/{$argArticleId}"



}
object AddArticleScreen : EniShopDestination {
    override val route: String
        get() = "articleFormScreenRoute"
}
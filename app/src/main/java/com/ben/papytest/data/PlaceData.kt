package com.ben.papytest


// SectionHeader.kt
data class SectionHeader(val title: String)

// SectionItem.kt
data class SectionItem(
    val name: String,
    val time: String,
    val address:String,
    var status:String
)
// this function will be replaced with actual data source or API call to get the actual data
 fun createDummyData(): List<Any> {
    val dataList = mutableListOf<Any>()
    dataList.add(SectionHeader("Tue, Jun 23"))
    dataList.add(SectionItem("garat blay street Lagos", "7:34am", "45 araola, hafina str , Lagos", "in progress"))
    dataList.add(SectionItem("garat blay street Lagos", "8:34am", "45 araola, hafina str , Lagos", "completed"))
    dataList.add(SectionItem("garat blay street Lagos", "9:34am", "45 araola, hafina str , Lagos", "in progress"))
    dataList.add(SectionItem("garat blay street Lagos", "7:34am", "45 araola, hafina str , Lagos", "Not started"))

    dataList.add(SectionHeader("Thu, July 8"))
    dataList.add(SectionItem("garat blay street Lagos", "7:34am", "45 araola, hafina str , Lagos", "completed"))
    dataList.add(SectionItem("garat blay street Lagos", "2:34am", "45 araola, hafina str , Lagos", "Not started"))
    dataList.add(SectionItem("garat blay street Lagos", "8:34am", "45 araola, hafina str , Lagos", "in progress"))
    dataList.add(SectionItem("garat blay street Lagos", "7:34am", "45 araola, hafina str , Lagos", "completed"))
    // Add more data items as needed
    return dataList
}


package com.example

/**
 * Created by catherine on 17/5/10.
 */

open class person(name: String, age: Int, college: String? = null) {
    var name = ""
    var age = 0
    var college = null

    init {
        this.show()
    }

    open fun show() {
        println(this)
    }

    override fun toString(): String {
        return "name=$name,age=$age,college=$college"
    }
}


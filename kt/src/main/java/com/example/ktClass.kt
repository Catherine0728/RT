package com.example

/**
 * Created by catherine on 17/5/9.
 */

open class Point2D
constructor(x: Int, y: Int) {
    var x: Int = -1
    var y: Int = -1

    init {
        this.show()
        this.x = x
        this.y = y
        println("primary constructor : point2D")
    }

    open fun show() {
        println(this)
    }

    constructor() : this(1, 1) {
        println("secondary constructor : point2D")
    }

    constructor(xy: Int) : this(xy, xy)


    override fun toString(): String {
        return "第一个参数x=$x,第二个参数y=$y"
    }

}

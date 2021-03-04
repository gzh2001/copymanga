package top.fumiama.copymanga.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ScrollRefreshView : NestedScrollView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        //Log.d("MyOSV", "$l, $t, $oldl, $oldt")
        swipeRefreshLayout?.isEnabled = t == 0
    }
}
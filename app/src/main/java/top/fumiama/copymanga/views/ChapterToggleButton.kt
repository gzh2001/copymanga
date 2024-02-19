package top.fumiama.copymanga.views

import android.content.Context
import androidx.appcompat.widget.AppCompatToggleButton;
import android.util.AttributeSet
import android.widget.ToggleButton

class ChapterToggleButton: AppCompatToggleButton {
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?): super(context, null)

    var url: CharSequence? = null
    var caption: CharSequence? = null
    var index: Int = 0
    var uuid: CharSequence? = null
    var chapterName: CharSequence = "null"
        set(value) {
            textOn = value
            textOff = value
            text = value
            field = value
        }
}
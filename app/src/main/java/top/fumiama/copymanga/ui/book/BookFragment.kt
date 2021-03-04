package top.fumiama.copymanga.ui.book

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.navigation.Navigation
import top.fumiama.dmzj.copymanga.R
import top.fumiama.copymanga.MainActivity.Companion.mainWeakReference
import top.fumiama.copymanga.template.NoBackRefreshFragment
import java.lang.Thread.sleep
import java.lang.ref.WeakReference

class BookFragment:NoBackRefreshFragment(R.layout.fragment_book) {
    private lateinit var bookHandler: BookHandler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isFirstInflate) {
            bookHandler = BookHandler(WeakReference(this), arguments?.getString("path")?:"null")
            Thread{
                sleep(600)
                bookHandler.startLoad()
            }.start()
        }
        else bookHandler.fbibinfo?.layoutParams?.height = (bookHandler.fbibinfo?.width?:0 * 4.0 / 9.0 + 0.5).toInt()
    }

    override fun onResume() {
        super.onResume()
        mainWeakReference?.get()?.menuMain?.let { setMenuVisible(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainWeakReference?.get()?.menuMain?.let { setMenuInvisible(it) }
    }

    private fun setMenuInvisible(menu: Menu){
        menu.findItem(R.id.action_download)?.isVisible = false
    }

    private fun setMenuVisible(menu: Menu) {
        Log.d("MyBF", "显示下载按钮")
        val dl = menu.findItem(R.id.action_download)
        dl?.isVisible = true
        dl?.setIcon(R.drawable.ic_menu_download)
        dl?.setOnMenuItemClickListener {
            if(bookHandler.complete && it.itemId == R.id.action_download){
                navigate2dl()
                true
            }
            else it.itemId == R.id.action_download
        }
    }

    private fun navigate2dl(){
        val bundle = Bundle()
        bundle.putString("path", arguments?.getString("path")?:"null")
        bundle.putString("name", bookHandler.book?.results?.comic?.name)
        val groups = bookHandler.book?.results?.groups
        var keys = arrayOf<String>()
        var gpws = arrayOf<String>()
        groups?.values?.forEach {
            keys += it.name
            gpws += it.path_word
        }
        bundle.putStringArray("group", gpws)
        bundle.putStringArray("groupNames", keys)
        rootView?.let { Navigation.findNavController(it).navigate(R.id.action_nav_book_to_nav_group, bundle) }
    }
}
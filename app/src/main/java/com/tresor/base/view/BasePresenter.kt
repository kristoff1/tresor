package com.tresor.base.view

/**
 * @author sebastianuskh on 5/22/17.
 */

abstract class BasePresenter<V : BaseView> {

    lateinit var view : V

}

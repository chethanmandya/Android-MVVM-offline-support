package com.chethan.sport.db
/**
 * Created by Chethan on 4/8/2019.
 */
import android.app.Application


/**
 * We use a separate App for tests to prevent initializing dependency injection.
 */
class TestApp : Application()


package com.yixun.pettyloan.adapter.multitype;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

/**
 * Process and flow operators for one-to-many.
 */
public interface OneToManyFlow<T> {

    /**
     * Sets some item view binders to the item type.
     *
     * @param binders the item view binders
     * @return end flow operator
     */
    @NonNull
    @CheckResult
    @SuppressWarnings("unchecked")
    OneToManyEndpoint<T> to(@NonNull ItemViewBinder<T, ?>... binders);
}

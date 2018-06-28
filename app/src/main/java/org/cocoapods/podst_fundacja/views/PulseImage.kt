package org.cocoapods.podst_fundacja.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.ImageView

class PulseImage(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {

    fun startAnnimation(){
        val handler = Handler()

        object : Runnable {
            override fun run() {
                val scalerX = ObjectAnimator.ofFloat(this@PulseImage,
                        "scaleX", 1f, 1.3f)
                val scalerY = ObjectAnimator.ofFloat(this@PulseImage,
                        "scaleY", 1f, 1.3f)
                val alpha = ObjectAnimator.ofFloat(this@PulseImage,"alpha", 0.5f,0f)

                val scalerX1 = ObjectAnimator.ofFloat(this@PulseImage,
                        "scaleX", 1.3f, 1f).setDuration(0)
                val scalerY1 = ObjectAnimator.ofFloat(this@PulseImage,
                        "scaleY", 1.3f, 1f).setDuration(0)
                val alpha1 = ObjectAnimator.ofFloat(this@PulseImage,"alpha", 1f).setDuration(0)

                val animatorSet = AnimatorSet()
                animatorSet.play(scalerX).with(alpha).with(scalerY)
                        .before(scalerX1).before(scalerY1).before(alpha1)
                animatorSet.start()

                handler.postDelayed(this, 1000)
            }
        }.run()
    }
}
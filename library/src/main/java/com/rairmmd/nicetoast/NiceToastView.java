package com.rairmmd.nicetoast;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rarimmd.nicetoast.R;


/**
 * @author Rair
 * @date 2017/11/2
 * <p>
 * desc:
 */

class NiceToastView extends RelativeLayout {

    private Context context;
    private TextView tvMsg;
    private ImageView leftImage;
    private RelativeLayout rlLayout;
    private LinearLayout llContent, llContentMain;
    private int width;
    private boolean constainWidth = false;
    private boolean show = false;

    public NiceToastView(Context context) {
        this(context, null);
    }

    public NiceToastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    private void initControl(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_nice_toast_view, this);
        assignUiElements();
    }

    private void assignUiElements() {
        width = dp2Pixel(context, 200);
        leftImage = findViewById(R.id.left_image);
        tvMsg = findViewById(R.id.tv_msg);
        rlLayout = findViewById(R.id.rl_layout);
        llContentMain = findViewById(R.id.ll_content_main);
        rlLayout.setAlpha(0f);
        llContent = findViewById(R.id.ll_content);

    }

    public NiceToastView setColor(int color) {
        GradientDrawable bgView = (GradientDrawable) llContentMain.getBackground();
        bgView.setColor(ContextCompat.getColor(context, color));
        return this;
    }

    public NiceToastView setBorderRetangle() {
        llContentMain.setBackgroundResource(R.drawable.shape_retangle);
        return this;
    }

    public NiceToastView setIcon(int drawable) {
        leftImage.setImageResource(drawable);
        return this;
    }

    public NiceToastView setScaleTypeIcon(ImageView.ScaleType scaleTypeIcon) {
        leftImage.setScaleType(scaleTypeIcon);
        return this;
    }

    public NiceToastView setMessage(String msg) {
        tvMsg.setText(msg);
        return this;
    }

    public NiceToastView setTextColor(int color) {
        tvMsg.setTextColor(color);
        return this;
    }

    public NiceToastView setWidth(int width) {
        this.width = dp2Pixel(context, width);
        return this;
    }

    private void iniciarConf() {
        if (!constainWidth) {
            ViewGroup.LayoutParams layoutParams = llContent.getLayoutParams();
            layoutParams.width = 0;
            llContent.setLayoutParams(layoutParams);
            tvMsg.setAlpha(0f);
            constainWidth = true;
        }

    }

    public void show() {
        iniciarConf();
        if (!show) {
            Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.scale_up);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ValueAnimator anim = ValueAnimator.ofInt(0, width);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = llContent.getLayoutParams();
                            layoutParams.width = val;
                            llContent.setLayoutParams(layoutParams);
                        }
                    });
                    anim.setDuration(600);
                    anim.start();
                    aplyAlpha(tvMsg, 800, 1, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            new CountDownTimer(500, 100) {

                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    hide();
                                }
                            }.start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            rlLayout.setAlpha(1f);
            rlLayout.startAnimation(animation1);
            show = true;
        }
    }

    private void hide() {
        aplyAlpha(tvMsg, 400, 0, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ValueAnimator anim = ValueAnimator.ofInt(width, 0);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = llContent.getLayoutParams();
                        layoutParams.width = val;
                        llContent.setLayoutParams(layoutParams);
                    }
                });
                anim.setDuration(600);
                anim.start();
                aplyAlpha(rlLayout, 650, 0, null);
                show = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    public static void aplyAlpha(View view, long delay, float alpha, Animator.AnimatorListener animatorListener) {
        ViewPropertyAnimator animator2 = view.animate();
        animator2.alpha(alpha);
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.setStartDelay(delay);
        animator2.setListener(animatorListener);
        animator2.start();
    }

    private int dp2Pixel(Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());

    }
}

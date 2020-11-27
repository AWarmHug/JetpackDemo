package com.bingo.jetpackdemo.ui.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bingo.jetpackdemo.R;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chebada.utils.ktx.UIKt;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 作者：warm
 * 时间：2018-06-12 17:34
 * 描述：
 */
public class GlideImageLoader {

    public static void loadBookDetailBg(Fragment fragment, View view, Object object) {
        GlideRequest<Bitmap> glide = GlideApp.with(fragment)
                .asBitmap()
                .load(object)
                .placeholder(0)
                .error(0)
                .transition(BitmapTransitionOptions.withCrossFade(150));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            glide.transforms(new BlurTransformation(25, 2));
        }
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        }
    }

    public static void loadBookCover(Context context, View view, Object object) {
        loadImage(context, view, object, 4);
    }

    public static void loadBookCover(Fragment fragment, View view, Object object) {
        loadImage(fragment, view, object, 4);
    }


    public static void loadHeaderImage(Fragment fragment, View view, Object object) {
        loadCircleImage(fragment, view, object);
    }

    public static void loadCircleImage(Fragment fragment, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error)
                .circleCrop();
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    public static void loadHeaderImage(Activity activity, View view, Object object) {
        loadCircleImage(activity, view, object);
    }


    public static void loadCircleImage(Activity activity, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(activity)
                .load(object)
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error)
                .circleCrop();
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    public static void loadImageNoPh(Context context, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .placeholder(0)
                .transition(DrawableTransitionOptions.withCrossFade(300));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    public static void loadImage(Context context, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .centerCrop();
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    public static void loadBannerImage(Context context, View view, Object object, int roundDp) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error);
        if (roundDp != 0) {
            glide.transforms(new CenterInside(), new RoundedCorners(UIKt.getDp(roundDp)));
        } else {
            glide.transforms(new CenterInside());
        }
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    public static void loadImage(Context context, View view, Object object, int roundDp) {
        GlideRequest<Drawable> glide = GlideApp.with(context)
                .load(object)
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error);
        if (roundDp != 0) {
            glide.transforms(new CenterCrop(), new RoundedCorners(UIKt.getDp(roundDp)));
        } else {
            glide.transforms(new CenterCrop());
        }
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    public static void loadImage(Fragment fragment, View view, Object object, int roundDp) {
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .placeholder(R.drawable.bg_image_holder)
                .error(R.drawable.bg_image_error)
                .transforms(new CenterCrop(), new RoundedCorners(UIKt.getDp(roundDp)));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }


    public static void loadImage(Fragment fragment, View view, Object object) {
        if (view == null) {
            return;
        }
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade(300));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    public static void loadImageSplash(Fragment fragment, View view, Object object) {
        GlideRequest<Drawable> glide = GlideApp.with(fragment)
                .load(object)
                .centerInside()
                .error(0)
                .placeholder(0)
                .encodeQuality(100)
                .transition(DrawableTransitionOptions.withCrossFade(300));
        if (view instanceof ImageView) {
            glide.into((ImageView) view);
        } else {
            glide.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    /**
     * 显示图片（fitXY）
     *
     * @param imageView 图像控件
     * @param iconUrl   图片地址
     */
    public static void loadImage(final ImageView imageView, Object iconUrl) {
        GlideApp.with(imageView)
                .asBitmap()
                .load(iconUrl)
                .centerInside()
                .transition(new BitmapTransitionOptions().crossFade(300))
                .into(imageView);
    }

    public static void resume(Context context) {
        GlideApp.with(context).resumeRequests();
    }

    public static void pause(Context context) {
        GlideApp.with(context).pauseAllRequests();
    }
}

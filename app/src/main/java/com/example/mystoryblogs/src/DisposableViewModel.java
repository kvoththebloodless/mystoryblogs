package com.example.mystoryblogs.src;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class DisposableViewModel extends ViewModel {
private CompositeDisposable compositeDisposable = new CompositeDisposable();
       public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
        }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

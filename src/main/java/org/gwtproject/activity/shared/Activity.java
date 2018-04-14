/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.activity.shared;

import java.util.function.Consumer;

import org.gwtproject.event.shared.EventBus;

/**
 * Implemented by objects that control a piece of user interface, with a life
 * cycle managed by an {@link ActivityManager}, in response to
 * {@link com.google.gwt.place.shared.PlaceChangeEvent} events as the user
 * navigates through the app.
 */
public interface Activity {
  
  /**
   * Marker interface for an Activity panel. It can be either a widget or an 
   * element. When setting up the {@link ActivityManager} the display should
   * do the proper checks & casting.
   */
  interface View {
    
  }
  
  /**
   * Called when the user is trying to navigate away from this activity.
   *
   * @return A message to display to the user, e.g. to warn of unsaved work, or
   *         null to say nothing
   */
  String mayStop();

  /**
   * Called when {@link #start} has not yet replied to its callback, but the
   * user has lost interest.
   */
  void onCancel();

  /**
   * Called when the Activity's view has been removed from container. All event
   * handlers it registered will have been removed before this method is called.
   */
  void onStop();

  /**
   * Called when the Activity should ready its view for the user. When the
   * view is ready (typically after an RPC response has been received),
   * receiver should present it by calling {@link Consumer#accept(Object)} on 
   * the given container.
   * <p>
   * Any handlers attached to the provided event bus will be de-registered when
   * the activity is stopped, so activities will rarely need to hold on to the
   * {@link com.google.gwt.event.shared.HandlerRegistration HandlerRegistration}
   * instances returned by {@link EventBus#addHandler}.
   *
   * @param container the container to display this activity's view when it is ready
   * @param eventBus the event bus
   */
  void start(Consumer<View> container, EventBus eventBus);
}

/***********************************************************************************************************************
 *
 * blueArgyle - a Java UI for Argyll
 * Copyright (C) 2011-2011 by Tidalwave s.a.s. (http://www.tidalwave.it)
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 * WWW: http://blueargyle.java.net
 * SCM: https://bitbucket.org/tidalwave/blueargyle-src
 *
 **********************************************************************************************************************/
package it.tidalwave.uniformity.measurement.ui;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.swing.Action;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/***********************************************************************************************************************
 *
 * @stereotype Presentation
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public interface UniformityCheckMeasurementPresentation 
  {
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @RequiredArgsConstructor(staticName="pos") @EqualsAndHashCode @ToString
    public static class Position
      {
        @Nonnegative
        public final int column;
        
        @Nonnegative
        public final int row;
      }

    /*******************************************************************************************************************
     *
     * Binds to some controller {@link Action}s.
     *
     ******************************************************************************************************************/
    public void bind (@Nonnull Action continueAction, @Nonnull Action cancelAction);
    
    /*******************************************************************************************************************
     * 
     * Sets the grid size.
     *
     ******************************************************************************************************************/
    public void setGridSize (@Nonnegative int rows, @Nonnegative int columns);
    
    /*******************************************************************************************************************
     *
     * Makes the UI visible.
     *
     ******************************************************************************************************************/
    public void showUp();
    
    /*******************************************************************************************************************
     *
     * Renders an empty cell at the given position.
     *
     ******************************************************************************************************************/
    public void renderEmptyCellAt (@Nonnull Position position);

    /*******************************************************************************************************************
     * 
     * Renders a cell with an invitation to place the sensor at the given position.
     *
     ******************************************************************************************************************/
    public void renderSensorPlacementInvitationCellAt (@Nonnull Position position);

    /*******************************************************************************************************************
     * 
     * Renders the control panel at the given position.
     *
     ******************************************************************************************************************/
    public void renderControlPanelAt (@Nonnull Position position);

    /*******************************************************************************************************************
     * 
     * Renders a white cell for the measurement at the given position.
     *
     ******************************************************************************************************************/
    public void renderWhiteCellAt (@Nonnull Position position);

    /*******************************************************************************************************************
     * 
     * Renders a cell with the measurement results at the given position.
     *
     ******************************************************************************************************************/
    public void renderMeasurementCellAt (@Nonnull Position position,
                                         @Nonnull String luminance, 
                                         @Nonnull String whitePoint);
    
    /*******************************************************************************************************************
     *
     * Dismisses the UI.
     *
     ******************************************************************************************************************/
    public void dismiss();
  }
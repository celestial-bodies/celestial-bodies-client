// Copyright 2008 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package edu.cnm.deepdive.celestialbodies.units;

/**
 * This class is used by the Celestial Bodies app.
 */
public class RaDec {
  public float ra;        // In degrees
  public float dec;       // In degrees

  public RaDec(float ra, float dec) {
    this.ra = ra;
    this.dec = dec;
  }

  @Override public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("RA: " + ra + " degrees\n");
    sb.append("Dec: " + dec + " degrees\n");
    return sb.toString();
  }

  // This should be relatively easy to do. In the northern hemisphere,
  // objects never set if dec > 90 - lat and never rise if dec < lat -
  // 90. In the southern hemisphere, objects never set if dec < -90 - lat
  // and never rise if dec > 90 + lat. There must be a better way to do
  // this...


  /**
   * Return true if the given Ra/Dec is always above the horizon. Return
   * false otherwise.
   * In the northern hemisphere, objects never set if dec greater than 90 - lat.
   * In the southern hemisphere, objects never set if dec less than -90 - lat.
   */
  public boolean isCircumpolarFor(LatLong loc) {
    if (loc.getLatitude() > 0.0f) {
      return (this.dec > (90.0f - loc.getLatitude()));
    } else {
      return (this.dec < (-90.0f - loc.getLatitude()));
    }
  }


  /**
   * Return true if the given Ra/Dec is always below the horizon. Return
   * false otherwise.
   * In the northern hemisphere, objects never rise if dec less than lat - 90.
   * In the southern hemisphere, objects never rise if dec greater than 90 - lat.
   */
  public boolean isNeverVisible(LatLong loc) {
    if (loc.getLatitude() > 0.0f) {
      return (this.dec < (loc.getLatitude() - 90.0f));
    } else {
      return (this.dec > (90.0f + loc.getLatitude()));
    }
  }
}
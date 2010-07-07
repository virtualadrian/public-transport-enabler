/*
 * Copyright 2010 the original author or authors.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.schildbach.pte;

import java.util.Date;
import java.util.List;

/**
 * @author Andreas Schildbach
 */
public final class Connection
{
	final public String id;
	final public String link;
	final public Date departureTime;
	final public Date arrivalTime;
	final public String from;
	final public String to;
	final public List<Part> parts;

	public Connection(String link, Date departureTime, Date arrivalTime, String from, String to, List<Part> parts)
	{
		this.id = extractId(link);
		this.link = link;
		this.departureTime = departureTime;
		this.from = from;
		this.arrivalTime = arrivalTime;
		this.to = to;
		this.parts = parts;
	}

	public static String extractId(String link)
	{
		return link.substring(link.length() - 10);
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder(getClass().getName() + "[");
		builder.append("id=").append(id);
		builder.append(",departureTime=").append(departureTime);
		builder.append(",arrivalTime=").append(arrivalTime);
		builder.append(",parts=").append(parts);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof Connection))
			return false;
		final Connection other = (Connection) o;
		return id.equals(other.id);
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}

	public static interface Part
	{
	}

	public final static class Trip implements Part
	{
		final public String line;
		final public int[] lineColors;
		final public String destination;
		final public Date departureTime;
		final public String departurePosition;
		final public String departure;
		final public Date arrivalTime;
		final public String arrivalPosition;
		final public String arrival;

		public Trip(final String line, final int[] lineColors, final String destination, final Date departureTime, final String departurePosition,
				final String departure, final Date arrivalTime, final String arrivalPosition, final String arrival)
		{
			this.line = line;
			this.lineColors = lineColors;
			this.destination = destination;
			this.departureTime = departureTime;
			this.departurePosition = departurePosition;
			this.departure = departure;
			this.arrivalTime = arrivalTime;
			this.arrivalPosition = arrivalPosition;
			this.arrival = arrival;
		}

		public Trip(final Date departureTime, final Date arrivalTime, final String line, final int[] lineColors)
		{
			this(line, lineColors, null, departureTime, null, null, arrivalTime, null, null);
		}

		@Override
		public String toString()
		{
			final StringBuilder builder = new StringBuilder(getClass().getName() + "[");
			builder.append("line=").append(line);
			builder.append(",");
			builder.append("destination=").append(destination);
			builder.append(",");
			builder.append("departure=").append(departureTime).append("/").append(departurePosition).append("/").append(departure);
			builder.append(",");
			builder.append("arrival=").append(arrivalTime).append("/").append(arrivalPosition).append("/").append(arrival);
			builder.append("]");
			return builder.toString();
		}
	}

	public final static class Footway implements Part
	{
		final public int min;
		final public String departure;
		final public String arrival;

		public Footway(int min, String departure, String arrival)
		{
			this.min = min;
			this.departure = departure;
			this.arrival = arrival;
		}

		@Override
		public String toString()
		{
			final StringBuilder builder = new StringBuilder(getClass().getName() + "[");
			builder.append("min=").append(min);
			builder.append(",");
			builder.append("departure=").append(departure);
			builder.append(",");
			builder.append("arrival=").append(arrival);
			builder.append("]");
			return builder.toString();
		}
	}
}